package src.tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class CodeAccounter {
        List<String> filePaths;

        Stack<String> stack;

        List<ClassInfo> classesInfo;

        int numClasses;

        int numMethods;

        int projectSize;

        public CodeAccounter() {
                this.classesInfo = new ArrayList<>();
                this.filePaths = new ArrayList<>();
                this.stack = new Stack<String>();
        }

        public void setFilePaths(File inputFilePaths) throws IOException, FileNotFoundException {
                Scanner scanner = null;

                try {
                        scanner = new Scanner(inputFilePaths);
                } catch (FileNotFoundException ex) {
                        throw ex;
                }

                while (scanner.hasNextLine()) {
                        String input = scanner.nextLine();

                        int indexExtension = input.lastIndexOf('.');
                        //System.out.format("indexExtension: %d\n", indexExtension);

                        if (indexExtension > 0) {
                                String extension = input.substring(indexExtension + 1);
                                //System.out.format("extension: %s\n", extension);
                                if ("java".equals(extension)) {
                                        //System.out.format("%s added to filePaths\n", input);
                                        this.filePaths.add(input);
                                } else {
                                        throw new IOException("Only able to read java code");
                                }
                        } else {
                                throw new IOException("File without extension");
                        }
                }
        }

        public void beginAccounting() throws FileNotFoundException {
                for (String filePath : this.filePaths) {
                        File file = new File(filePath);

                        //System.out.format("filePath: %s\n", filePath);

                        analyseFile(file);
                }
        }

        void analyseFile(File file) throws FileNotFoundException {
                Scanner scanner = null;

                try {
                        scanner = new Scanner(file);
                } catch (FileNotFoundException ex) {
                        throw ex;
                }

                int outsideLines = 0;

                while (scanner.hasNextLine()) {
                        String input = scanner.nextLine();
                        //System.out.format("input: %s\n", input);

                        if (input.isEmpty()) {
                                //System.out.println("empty line\n");
                                continue;
                        }

                        if (isComment(scanner, input)) {
                                //System.out.format("comment line: %s\n", input);

                                continue;
                        }

                        int indexOfClassToken = getIndexOfClassToken(input);

                        if (indexOfClassToken == -1) {
                                //System.out.format("outside class line: %s\n", input);

                                outsideLines += 1;
                        } else {
                                String className = getClassName(input, indexOfClassToken);
                                //System.out.format("className: %s\n", className);

                                ClassInfo newClass = handleClassContent(scanner, className);
                                //System.out.format("outsideLines: %d\n", outsideLines);

                                newClass.increaseSize(outsideLines);
                                //System.out.format("Class size: %d\n", newClass.getSize());

                                this.classesInfo.add(newClass);
                        }
                }
        }

        String getClassName(String input, int indexOfClassToken) {
                int startOfClassName = indexOfClassToken + 6;
                int endOfClassName = input.indexOf(" ", startOfClassName);
                return input.substring(startOfClassName, endOfClassName);
        }

        int getIndexOfClassToken(String input) {
                return input.indexOf("class ");
        }

        ClassInfo handleClassContent(Scanner scanner, String className) {
                ClassInfo classInfo = new ClassInfo(className, 2);
                this.numClasses += 1;

                while (true) {
                        if (!scanner.hasNextLine()) {
                                break;
                        }

                        String input = scanner.nextLine();
                        //System.out.format("input: %s\n", input);

                        if (input.isEmpty()) {
                                //System.out.println("empty line\n");

                                continue;
                        }

                        if (isComment(scanner, input)) {
                                //System.out.println("comment line\n");

                                continue;
                        }

                        Pattern pattern_function = Pattern.compile("\\S*[(][\\sa-zA-Z\\[\\]\\,]*[)][\\sa-zA-Z\\,]*[{]", Pattern.CASE_INSENSITIVE);
                        Matcher matcher_function = pattern_function.matcher(input);
                        boolean matchFound_function = matcher_function.find();

                        if (matchFound_function) {
                                //System.out.println("method found\n");
                                String methodName = getMethodName(input);
                                //System.out.format("methodName: %s\n", methodName);

                                int methodSize = handleMethodContent(scanner, methodName);
                                //System.out.format("methodSize: %s\n", methodSize);

                                classInfo.addMethodInfo(methodName, methodSize);

                                continue;
                        }

                        int indexOfInnerClassToken = getIndexOfClassToken(input);

                        if (indexOfInnerClassToken != -1) {
                                //System.out.println("innerClass found\n");
                                String innerClassName = getClassName(input, indexOfInnerClassToken);
                                //System.out.format("innerClassName: %s\n", innerClassName);

                                classInfo.addInnerClass(innerClassName);

                                ClassInfo innerClassInfo = handleClassContent(scanner, innerClassName);
                                this.classesInfo.add(innerClassInfo);

                                continue;
                        }

                        int indexOfCloseBracketToken = getIndexOfCloseBracket(input);

                        if (indexOfCloseBracketToken != -1) {
                                //System.out.println("end of class\n");

                                break;
                        }

                        classInfo.increaseSize(1);
                        //System.out.println("Class size increased by one\n");
                }

                return classInfo;
        }

        int getIndexOfCloseBracket(String input) {
                Pattern pattern_closeBracket = Pattern.compile("^[\\s]*[}][\\s]*", Pattern.CASE_INSENSITIVE);
                Matcher matcher_closeBracket = pattern_closeBracket.matcher(input);
                boolean matchFound_closeBracket = matcher_closeBracket.find();

                if (matchFound_closeBracket) {
                        return 1;
                }

                return -1;
        }

        int getIndexOfOpenBracket(String input) {
                Pattern pattern_openBracket = Pattern.compile("[\\s]*[{][\\s]*$", Pattern.CASE_INSENSITIVE);
                Matcher matcher_openBracket = pattern_openBracket.matcher(input);
                boolean matchFound_openBracket = matcher_openBracket.find();

                if (matchFound_openBracket) {
                        return 1;
                }

                return -1;
        }

        boolean isComment(Scanner scanner, String input) {
                Pattern pattern_dslash = Pattern.compile("[\\s]*//", Pattern.CASE_INSENSITIVE);
                Matcher matcher_dslash = pattern_dslash.matcher(input);
                boolean matchFound_dslash = matcher_dslash.find();

                if (matchFound_dslash) {
                        return true;
                }

                Pattern pattern_asterisk = Pattern.compile("[\\s]*/[*]", Pattern.CASE_INSENSITIVE);
                Matcher matcher_asterisk = pattern_asterisk.matcher(input);
                boolean matchFound_asterisk = matcher_asterisk.find();

                if (matchFound_asterisk) {
                        String nextInput = scanner.nextLine();

                        pattern_asterisk = Pattern.compile("[*]/[\\s]*", Pattern.CASE_INSENSITIVE);
                        matcher_asterisk = pattern_asterisk.matcher(nextInput);
                        matchFound_asterisk = matcher_asterisk.find();

                        while (!matchFound_asterisk) {
                                nextInput = scanner.nextLine();

                                matcher_asterisk = pattern_asterisk.matcher(nextInput);
                                matchFound_asterisk = matcher_asterisk.find();
                        }

                        return true;
                }

                return false;
        }

        String getMethodName(String input) {
                int endOfMethodName = input.indexOf("(");
                String subStrForMethodName = input.substring(0, endOfMethodName);
                int startOfMethodName = subStrForMethodName.lastIndexOf(" ") + 1;
                return input.substring(startOfMethodName, endOfMethodName);
        }

        int handleMethodContent(Scanner scanner, String methodName) {
                this.numMethods += 1;

                int methodSize = 1;

                this.stack.add(methodName);

                while (!this.stack.empty()) {
                        if (!scanner.hasNextLine()) {
                                break;
                        }

                        String input = scanner.nextLine();
                        //System.out.format("input: %s\n", input);

                        if (input.isEmpty()) {
                                //System.out.println("empty line\n");

                                continue;
                        }

                        if (isComment(scanner, input)) {
                                //System.out.println("comment line\n");

                                continue;
                        }

                        int indexOfOpenBracketToken = getIndexOfOpenBracket(input);
                        int indexOfCloseBracketToken = getIndexOfCloseBracket(input);

                        if (indexOfOpenBracketToken != -1 && indexOfCloseBracketToken != -1) {
                                methodSize += 1;
                                //System.out.println("double bracket found\n");
                                //System.out.format("Stack size: %d\n", this.stack.size());

                                continue;
                        }

                        if (indexOfOpenBracketToken != -1) {
                                methodSize += 1;
                                this.stack.add("{");
                                //System.out.println("open bracket found\n");
                                //System.out.format("Stack size: %d\n", this.stack.size());

                                continue;
                        }

                        if (indexOfCloseBracketToken != -1) {
                                methodSize += 1;
                                this.stack.pop();
                                //System.out.println("close bracket found\n");
                                //System.out.format("Stack size: %d\n", this.stack.size());

                                continue;
                        }

                        methodSize += 1;
                }

                return methodSize;
        }

        public void getProgramReport(String projectName) {
                int numRows = this.numClasses + this.numMethods + 1;
                String[][] matrix = new String[numRows][4];

                int i = 0;
                int j = 0;
                String blankSpace = " ";

                for (ClassInfo classIn : this.classesInfo) {
                        int sum = 0;
                        sum += classIn.getSize();

                        Map<String, Integer> methodsClassIn = classIn.getMethods();

                        for (Map.Entry<String, Integer> method : methodsClassIn.entrySet()) {

                                sum += method.getValue();

                                matrix[i][j] = projectName;
                                j++;
                                matrix[i][j] = classIn.getName();
                                j++;
                                matrix[i][j] = method.getKey();
                                j++;
                                matrix[i][j] = Integer.toString(method.getValue());

                                i++;
                                j -= 3;
                        }

                        matrix[i][j] = projectName;
                        j++;
                        matrix[i][j] = classIn.getName();
                        j++;
                        matrix[i][j] = blankSpace;
                        j++;
                        matrix[i][j] = Integer.toString(sum);

                        this.projectSize += sum;

                        i++;
                        j -= 3;
                }

                matrix[i][j] = projectName;
                j++;
                matrix[i][j] = blankSpace;
                j++;
                matrix[i][j] = blankSpace;
                j++;
                matrix[i][j] = Integer.toString(this.projectSize);

                JFrame frame = new JFrame();
                String[] column = {"Program's name", "Class's name", "Method's name", "Size (LLOC)"};
                JTable jtable = new JTable(matrix, column);
                jtable.setBounds(30, 40, 200, 300);
                JScrollPane scrollPane = new JScrollPane(jtable);
                frame.add(scrollPane);
                frame.setTitle("Program's Report");
                frame.setSize(600, 400);
                frame.setVisible(true);
        }
}

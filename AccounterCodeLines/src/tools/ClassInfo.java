package src.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ClassInfo {
        String name;

        int size;

        List<String> innerClasses;

        List<MethodInfo> methods;

        ClassInfo(String name, int size) {
                this.name = name;
                this.size = size;
                this.innerClasses = new ArrayList<>();
                this.methods = new ArrayList<>();
        }

        class MethodInfo {
                String name;

                int size;

                MethodInfo(String name, int size) {
                        this.name = name;
                        this.size = size;
                }

                public String getName() {
                        return this.name;
                }

                public int getSize() {
                        return this.size;
                }
        }

        public void increaseSize(int amount) {
                this.size += amount;
        }

        public void addInnerClass(String innerClass) {
                this.innerClasses.add(innerClass);
        }

        public void addMethodInfo(String methodName, int methodSize) {
                MethodInfo method = new MethodInfo(methodName, methodSize);
                this.methods.add(method);
        }

        public String getName() {
                return this.name;
        }

        public int getSize() {
                return this.size;
        }

        public List<String> getInnerClasses() {
                return this.innerClasses;
        }

        public Map<String, Integer> getMethods() {
                Map<String, Integer> methodsMap = new HashMap<String, Integer>();

                for (MethodInfo method : this.methods) {
                        methodsMap.put(method.getName(), method.getSize());
                }

                return methodsMap;
        }
}

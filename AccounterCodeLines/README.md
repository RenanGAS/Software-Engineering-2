Para compilação:

- javac src/Main.java src/tools/*.java

Para execução:

- java src.Main programName filePaths.txt
    - programName: Nome do programa a ser quantificado (arbitrário)
    - filePaths.txt: arquivo com os caminhos absolutos dos arquivos do programa

Para checagem de estilo:

- java -jar checkstyle-10.12.3-all.jar -c bcc_engsw2_checks.xml ./src

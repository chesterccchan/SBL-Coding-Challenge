# Getting Started
This is a DNA Sequence Deduplication API with a simple front end. There will be an input file to store some equal length DNA sequences (src\main\resources\input.txt). You can edit the file to retrieve different result or user the front end to change the DNA Sequence.

### Guides

1. Start the application by command: ./mvnw spring-boot:run
2. After the application was started, open a browser and go to localhost:8080/result
3. There will provide a set of preloaded DNA Sequences and a default vault of Threshold. You can try to modify the DNA Sequences and Threshold to retrieve different result.
4. After submit the form, it is show up two table. Once is the Unique list, another is the Duplicate list.

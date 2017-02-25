CS535 Introduction to Data Mining
Fall 2016
PROJECT README FILE

FILES:

[
  Included with this project are 9 files:

  Driver.java, the main file associated with the program also contains main
  Initializer.java, the file associated with initializing all the required data structutes
  ModelBuilder.java. the file associated with developing the model and predicting missing values
  FileProcessorInterface.java, the file associated with all the file operations
  FileProcessor.java, the file associated with all the file operations
  build.xml, the file associated with cleaning, compiling and running the code
  train_all_txt.txt, the associated input file
  output.txt, the associated output file
  README.txt the text file you are presently reading
]

EXECUTION ENVIRONMENT:

[
  The code is tested on remote.cs.binghamton.edu and bingsuns.binghamton.edu
]

SAMPLE OUTPUT:

[
  Total time is 48533 ms
]

TO UNZIP:

[
  unzip upasani_abhishek.zip
]

TO COMPILE:

[
  Assume you are in the directory containing this README, compile the code using:
  ant -buildfile build.xml all
]

TO RUN:

[
  Assume you are in the directory containing this README, run the code using:
  ant -buildfile build.xml run -Darg0=train_all_txt.txt -Darg1=output.txt
]

TO CLEAN:

[
  Assume you are in the directory containing this README, remove the executables using:
  ant -buildfile build.xml clean
]

TO GENERATE JAVADOCS:

[
  Assume you are in the directory containing this README, generate java docs using:
  ant -buildfile build.xml docs
]

BIBLIOGRAPHY:

[
  http://www.cs.carleton.edu/cs_comps/0607/recommend/recommender/itembased.html
]
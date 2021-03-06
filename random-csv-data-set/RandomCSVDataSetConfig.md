# Random CSV Data Set Config 

Random CSV Data Set Config is used to read CSV rows from file, split and put them into JMeter variables in random order.

This plugin has following options that affect the behavior:
  * `Filename` - path to CSV file. Relative path are resolved with respect to the path of the active test plan. For distributed testing, the CSV file must be stored on the server host system in the correct relative directory to where the JMeter server is started.;
  * `File encoding` - encoding of this CSV file;
  * `Delimiter` - delimiter that be used to split records in the file;
  * `Variable Names` - list (comma-separated) of variable names;
  * `Random order`  - reading in random order;
  * `Rewind on end of list` - if the flag is selected and an iteration loop has reached the end, the new loop will be started;
  * `First line is CSV header` - select this flag to skip header(used only if `Variable Names` is not empty);
  * `Independent list per thread` - determines that CSV data set will be shared for all threads or each thread will be having own local copy.

_***At the beginning of the test, the config reads file. There are a delay and a large memory consumption for large files.***_

_In preview area shows only 20 records from CSV file._

![](randomCSVDataSetConfig.png)

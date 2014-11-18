JmeterBundle-src
================

JmeterBundle is just a wrapper around the apache jmeter software, which allows access to be used with OASIS/FitNesse.

BUILD Instructions
==================
To rebuild and run JmeterBundle you must have Apache Ant installed and in the current PATH.

This simplest way to build and run JMeter is to simply execute the "run-jmeter.bat" file.  This will automatically
resolve all dependencies, compile, package and execute JMeter.


To download additional jars and source code needed for building the code and documentation:

  ant resolve

To build JMeter from source:
  ant [install]

To rebuild:
  ant clean install
  
To run JMeter GUI or a jmx:
  ant run
  
  ant run -Djmx=test/SimpleHttpTest1.jmx

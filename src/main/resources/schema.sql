CREATE TABLE fund (
  FundCode varchar(10) NOT NULL,
  FundName varchar(100) DEFAULT NULL,
  BenchMarkCode varchar(10) DEFAULT NULL,
  PRIMARY KEY (FundCode)
);

CREATE TABLE benchmark (
  BenchmarkCode varchar(10) NOT NULL,
  BenchmarkName varchar(100) DEFAULT NULL,
  PRIMARY KEY (BenchmarkCode)
);

CREATE TABLE returnseries (
  code varchar(10) DEFAULT NULL,
  Date date DEFAULT NULL,
  ReturnPerc decimal(10,6) DEFAULT NULL
);

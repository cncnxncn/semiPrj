CREATE TABLE "SCOTT"."SALES" 
   (	"MNUM" NUMBER, 
	"NAME" VARCHAR2(20 BYTE), 
	"PRICE" NUMBER, 
	 PRIMARY KEY ("MNUM"));
	 
	 CREATE TABLE "SCOTT"."MENUBILL" 
   (	"NAME" VARCHAR2(20 BYTE), 
	"PRICE" NUMBER);
	
	CREATE TABLE "SCOTT"."DAYSALES" 
   (	"YEAR" NUMBER, 
	"MONTH" NUMBER, 
	"DAYS" NUMBER, 
	"SALES" NUMBER
   );
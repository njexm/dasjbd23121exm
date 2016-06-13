--创建表空间
CREATE SMALLFILE TABLESPACE "EXM" DATAFILE 'C:\app\DeFei\oradata\orcl\EXM.DBF' SIZE 500M AUTOEXTEND ON NEXT 200M MAXSIZE UNLIMITED LOGGING EXTENT MANAGEMENT LOCAL SEGMENT SPACE MANAGEMENT AUTO;

--创建用户并赋予表空间
CREATE USER exm IDENTIFIED BY exmPWD DEFAULT TABLESPACE EXM;

--给用户赋予权限
grant create job to exm;
grant create trigger to exm;
grant create session to exm;
grant create sequence to exm;
grant create synonym to exm;
grant create table to exm;
grant create view to exm;
grant create procedure to exm;
grant alter session to exm;
grant execute on ctxsys.ctx_ddl to exm;
grant dba to exm;
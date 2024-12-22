# Full-stack-web-project

My first open source full-stack development project, used to LEARN and SHOW, also my interest. BTW, Welcome to contact me.  

I will directly describe my project and ideas in English, please ignore the grammar. I don't want to optimize the grammar like publish a research paper. LoL

The project is not finished, but I will keep updating it.

## 1.Database

### 1.1 Relational Database

#### 1.1.1 MySQL

First, let me clarify some easy topics.  
- MySQL Community (GPL) Downloads
In the mysql website, there are many download links, but most of them are for 'MySQL Community (GPL) Downloads'. GPL mean GNU General Public License, so it is free for use anywhere, you don't need to worry about the risk of charge.
- what is 'MySQL'?
In most sitatuation, the 'MySQL' we talk about is mean 'MySQL Community Server'. So when we talk about the mysql version is MySQL Community Server version.
why I say this? Because I will talk about the mysql client version in the next topic, which is 'MySQL Workbench'. 
- MySQL Workbench
In most sitatuation, we don't want to use the command line to operate the database, so we use the GUI to operate the database. I use the 'MySQL Workbench' to operate the MySQL server. BTW, there are many other clients in market, you can choose one.
- Why I clarify this?
MySQL Community Server and MySQL Workbench is most use in the development. In many(LoL, in my opinion is most) sitatuation, they don't know which 'MySQL' talk about. MySQL Community Server has it own vsrsion, and MySQL Workbench has it own version too. So we need to choose match version for both two. 
- My project
Besides download Community Server and Workbench in mysql website, they provide 'MySQL Installer' for windows developer, include server, workbench, and other mysql products. It is have two versions: '5.7.44' and '8.0.40' (Oct. 2024). Is it familiar to you? They always talk about 5.7 and 8.0 of MySQL vsesion is THIS!!! 
In my project, I choose 8.0.40.
- **Summery**
**In my project, I use 8.0.40 version of MySQL Community Server and MySQL Workbench as the database and database maintenance tool.**

Sencondly, let me talk about the main database design ideas and maintan rules.
- ID as Primary Key: auto increment id as primary key. Because it more efficient as index for serach. Also, it easy and usefull way of almost sitatuation.
- ID REFERENCE as Foreign Key: some tables have join relationship, use this to keep data complete.
- UUID (Universally Unique Identifier) as Fake(Logic) Primary Key: Prepare for data migration, data synchronization, database sharding, distributed architecture and so on. To keep the data unique in GLOBAL.
- NF (Normal Form): 1NF, 2NF, 3NF(I use), and some de-normalized.
- Maintan Rules One: Change the configuration of mysql server, open and edit my.ini file. I use to change the default path of data.
- Maintan Rules Two: Set up user roles.
- Maintan Rules Three: Backup data on time. I use Task Scheduler with mysqldump (In Linux is different way).
- **Summery**
**In my project, sqlscript.sql describe the table struct**

## 2.Server Side (Back-end Development)

First, let me clarify some easy topics.   

- OpenJDK vs Oracle JDK
OpenJDK is open source, and Oracle JDK is not open source. But in most sitatuation, they are same. To avoid the risk of charge, I use OpenJDK in my project.

- Spring boot
Open source, easy to connect spring cloud, stability, etc. Honestly, Because most companies use it, I use it too.

- Tips
Be careful of version at each framework. If use the wrong version, it will cause some unbelievable problems, even can't find the solution and can't run the application. LOOK AT THE pom.xml.

- **Summery**
Java with Spring boot is used in my project as server side. Other high level midle ware like Redis, Kafka, etc will add in future.


## 3.Browser Side (Front-end Development)

First, let me clarify some easy topics.   

- Vite plus Vue 


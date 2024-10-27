# Full-stack-web-project

My first open source full-stack development project, used to LEARN and SHOW, also my interest. BTW, Welcome to contact me.  

I will directly describe my project and ideas in English, please ignore the grammar. I don't want to optimize the grammar like publish a research paper. LoL

The project is not finished, but I will keep updating it.

## 1.Database

### 1.1 Relational Database

#### 1.1.1 MySQL

First, let me clarify some easy topics.   
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

Sencondly, let me talk about the database design ideas and maintan rules.


change the configuration of mysql server, open and edit my.ini file. I use to change the default path of data.





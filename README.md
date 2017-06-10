# mybatis_project
1.GitHub Basement:
   本地项目上传到远程仓库
     i.建立本地git仓库
       git init
       git add .
       git commit -am "comment"
     ii.GitHub 建立远程仓库
        https://github.com/simplezzy/project_name.git
     iii.本地仓库提交到远程仓库
         增加远程仓库 
           git remote add origin https://github.com/simplezzy/project_name.git
           git remote -v
           git pull origin master 
           git push origin master 

2.MyBatis Details
  a. Hibernate & JPA：全自动全映射ORM框架，消除sql，HQL；
                      全映射框架，当POJO字段太多，进行部分映射时比较困难，数据库性能下降
                      sql由框架内部自动生成，不便优化，对于长难复杂的SQL，处理不易，必须深入HQL
    MyBatis:  半自动、轻量级的持久化层框架 
              sql与java编码分离，功能边界清晰，一个专注于业务，一个专注于数据；
              由开发人员控制sql，便于优化

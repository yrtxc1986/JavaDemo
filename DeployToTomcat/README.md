Add file setting.xml to <UserDIR>/.m2/

Add tomcat-user with role "manager-gui, manager-script"

-----------------
<role rolename="manager-gui"/>
<role rolename="manager-script"/>
<user username="tomcat" password="tomcat" roles="manager-gui, manager-script"/>
-----------------

run "mvn tomcat7:redeploy"
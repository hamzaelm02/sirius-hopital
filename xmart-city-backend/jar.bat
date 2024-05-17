@echo off
set VM_server=client
set VM_HOST=172.31.249.196
set JAR_PATH=C:\Users\info\Desktop\proto vF\prototype-ing1\xmart-city-backend\target\xmart-zity-backend-1.0-SNAPSHOT-jar-with-dependencies.jar


echo Exécution du maven...
call mvn clean compile assembly:single install

echo envoyer le jar...
call scp  %JAR_PATH% %VM_server%@%VM_HOST%:\home\client

echo tuer le pid...
call ssh  %VM_server%@%VM_HOST% kill $(lsof -ti:45065)


echo Exécution du JAR dans la VM...

rem "Exécutez la commande à distance sur la VM en utilisant SSH"
ssh  %VM_server%@%VM_HOST% java -jar "xmart-zity-backend-1.0-SNAPSHOT-jar-with-dependencies.jar"

echo Terminé.
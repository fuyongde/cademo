#1生成根证书key
openssl genrsa -out certs/ca.key

#2生成根证书的csr
openssl req -new -key certs/ca.key -out certs/ca.csr

#3生成根证书
openssl x509 -req -days 3650 -in certs/ca.csr -signkey certs/ca.key -out certs/ca.crt

#4生成服务端证书的key
openssl genrsa -out certs/server.key

#5生成服务器生疏的csr
openssl req -new -key certs/server.key -out certs/server.csr

#6生成服务器证书
openssl ca -in certs/server.csr -cert certs/ca.crt -keyfile certs/ca.key -out certs/server.crt -days 365

#7创建客户端证书
openssl genrsa -des3 -out certs/client.key 1024
openssl req -new -key certs/client.key -out certs/client.csr
openssl ca -in certs/client.csr -cert certs/ca.crt -keyfile certs/ca.key -out certs/client.crt -days 364
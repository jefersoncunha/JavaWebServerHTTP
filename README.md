# JavaWebServerHTTP

Desenvolver um Web Server que implemente o método GET (http) e que disponibilize uma
única página de boas vindas com as seguintes características:
- A página de boas vindas deverá conter informações meteorológicas locais como:
temperatura, umidade, precipitação, e outras características que se julgar
conveniente;
- Deverá registrar para cada acesso, o endereço IP de origem da requisição em um
arquivo de log;
- Deverá ser implementado com múltiplas threads, onde cada requisição ao servidor
será tratada em uma thread exclusiva;
- A cada 30 segundos as informações meteorológicas serão atualizadas, de forma que a
página ficará bloqueada enquanto isto ocorre;
- As informações meteorológicas podem ser obtidas de forma randômica ou a partir de
um webservice (INMET, INPE, etc..). Para obter a nota integral neste item, a
implementação deverá ser com webservice.

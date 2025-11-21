# Notifications App
### Descrição
Este é um projeto feito como estudo de microserviços no Java com SpringBoot.
<br>

### Conteúdo
Dentro deste repositório é possível encontrar 2 pastas de mail e user. Cada uma é um microserviço diferente
<br>

### Microserviço User
Este microserviço cuida da autenticação, criação e atualização simples de usuários no sistema.
Cria uma conexão com um banco PostgreSQL e envia notificações para uma Exchange no RabbitMQ
<br>

### Microserviço Mail
Este microserviço cuida do recebimento de notificações via RabbitMQ enviadas pelo microserviço de User para criação e atualização simples de usuários no sistema.
Não envia efetivamente emails, apenas cria um log no terminal com a informação recebida pela Fila

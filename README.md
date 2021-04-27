# API-Controle-de-Pedidos
API de controle de Pedidos entre Cliente e Produtos

Para a execução da API é necessário configurar a mesma em um conexão com o banco de dados Postgresql:
* Criar um banco de dados no Postgresql chamado: managerCustomersProducts
* Ir até o arquivo application.properties do projeto e ajustar as configurações com base na configuração do banco para assim poder fazer a conexão.

Na implementação do projeto como um todo fiz duas adições: 
* Adicionei uma tabela chamada item_pedidopk contendo as FK de produto e pedido, onde é salvo os ids de ambas as entidades sempre que um novo pedido é cadastrado, 
facilitando a usabilidade de pedidos completos, já que o produto é cadastrado individualmente e com isso não contem relação alguma com pedido na base de dados.

* Adicionei um atributo "habilitado" nas entidades Cliente, Produto e Pedido para obter o controle de exclusão dos dados dessas entidades, pois não é possível excluir 
definitivamente os dados da base de dados devido as relações de FK que contem entre as tabelas, então com esse atributo se tem um controle de desabilitar os dados 
ao envés de excluir definitivamente.

Observação: Java 8

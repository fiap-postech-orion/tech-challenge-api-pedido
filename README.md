<h1 align="center">Tech Challenge Fase 1 - Turma 4SOAT - Grupo 17</h1>  
Este projeto é uma aplicação backend construída com uma arquitetura hexagonal (também conhecida como arquitetura porta e adaptador).   
A aplicação utiliza um banco de dados PostgreSQL para armazenar dados relacionados a clientes, produtos e pedidos.  

<h3>Configuração e Execução</h3>  
Clone o repositório para sua máquina local.

Certifique-se de ter o Docker instalado e configurado.

Execute o comando: ``` docker compose up -d ``` para subir a aplicação com o banco de dados.

As APIs da aplicação ficam acessíveis no endereço ``` http://localhost:8080 ```


## APIs Disponíveis

As APIs disponíveis são: <br />  
**1. Criação, Edição e Busca do Cliente** <br />  
**2. Criação, Edição, Remoção e Busca de Produto**<br />  
**3. Busca de Produtos por Categoria**<br />  
**4. Criação de pedidos sem identificação do cliente, pedido com o cliente e Busca de Pedidos**<br />

Para utilizar as APIs importe o arquivo: https://github.com/pietroow/tech-challenge-pos-tech/blob/main/Tech-challenge.postman_collection.json no postman

### Passos para Implantação

Implantação com Terraform na AWS

Esta seção descreve o processo de implantação da infraestrutura e da aplicação utilizando o Terraform para provisionar uma VPC, uma instância de banco de dados RDS e um cluster EKS na AWS.

Pré-requisitos

-	Terraform instalado e configurado em sua máquina local.
-	Acesso configurado à AWS CLI com as permissões necessárias para criar recursos na AWS

Passos para Implantação

#### 1.	Inicializar o Terraform
  Inicialize o Terraform para instalar os plugins necessários e preparar o ambiente de trabalho:
  ```
 terraform init
  ```

#### 2.	Criar a Infraestrutura na AWS

 Execute o Terraform para criar a VPC, sub-redes, gateways, o cluster EKS e uma         instância de banco de dados RDS:
```
 terraform apply -auto-approve
```
Esse comando irá provisionar:

- Uma VPC customizada para o seu cluster EKS.
- Sub-redes privadas e públicas dentro da VPC.
- Uma instância de banco de dados RDS configurada para uso com PostgreSQL.
- Um cluster EKS com os recursos necessários.

#### 3. Acessar a Aplicação

Após o provisionamento do cluster EKS, você poderá configurar o `kubectl` para interagir com o cluster:
```
aws eks --region <regiao> update-kubeconfig --name <nome_do_cluster>
```
Para acessar a aplicação, localize o endpoint exposto pelo Load Balancer:
```
kubectl get svc
```
A aplicação será acessível no endereço IP externo provido pelo serviço Load Balancer na porta especificada.

### Limpeza

Quando não for mais necessário, você pode destruir toda a infraestrutura criada pelo Terraform:
```
terraform destroy
```
Este comando irá desalocar todos os recursos provisionados na AWS, como a VPC, o cluster EKS e a instância RDS.
Atenção: Certifique-se de ter backups apropriados antes de destruir o banco de dados RDS se você estiver armazenando dados críticos.



# Vídeo explicativo
Este vídeo foi produzido para apresentar o progresso da fase 3 do projeto. Devido à sua extensão, foi necessário dividi-lo em duas partes.

Parte 1
https://youtu.be/aJEGGsBnekU

Parte 2 
https://youtu.be/hrz_YRGDWoc

-- Comandos Curso Angular (LOIANE)
link github: https://github.com/loiane/curso-angular

--------------------------------
-- ## REFERêNCIAS DOCUMENTACAO --
--------------------------------
-- Documentação Angular
link: https://angular.io/

-- Angular Material
link: https://material.angular.io/

-- Material Design - Cores e temas
link: https://material.io/design

-- Material Design - colors
link: https://material.io/design/color/the-color-system.html#tools-for-picking-colors

-- Biblioteca dos icones Material Design
link: https://developers.google.com/fonts/docs/material_icons

-----------------------------
-- ## 1 - COMANDOS ANGULAR --
-----------------------------

-- Criar um projeto novo
-- Na pasta do projeto digitar o seguinte comando:
comando: ng new <nome-do-projeto>
ex: ng new crud-spring

-- Iniciar o servidor
comando: ng serve

-- Instalando biblioteca angular material
comando: ng add @angular/material

-- Criando um módulo
comando: ng generate module <nome do modulo> ou ng g m <nome do modulo>
ex: ng g m courses

-- Criando um módulo com roteamento
comando: ng generate module <nome do modulo> --routing ou ng g m <nome do modulo> --routing
ex: ng g m courses --routing

-- Criando um componente
comando: ng generate component <caminho/nome do component> ou ng g m <caminho/nome do component>
ex: ng g c courses/courses

-- Criando uma interface
comando: ng generate interface <caminho/nome da interface> ou ng g i <caminho/nome do interface>
ex: ng g i courses/model/course

-- Criando uma service
comando: ng generate service <caminho/nome da service> ou ng g s <caminho/nome da service>
ex: ng g s courses/service/courses

-- Criando uma pipe
comando: ng generate pipe <caminho/nome do pipe> ou ng g pipe <caminho/nome do pipe>
ex: ng g pipe shared/pipes/category

-- Criando um resolver
comando: ng generate resolver <caminho/nome do resolver> ou ng g resolver <caminho/nome do resolver>
ex: ng g resolver couses/guards/course

-- Atualizacao Angular
links: https://www.npmjs.com/package/@angular/core
       https://update.angular.io/
       https://github.com/angular/angular/releases
       
comando que verifica o que pode ser atualizado..............: ng update 
comando para atualização de uma versão do angular (CLI).....: ng update @angular/cli@14.2.9
comando para atualização de uma versão do angular (CORE)....: ng update @angular/core@14.2.10
comando para atualização de uma versão do angular (CDK).....: ng update @angular/cdk@14.2.7
comando para atualização de uma versão do angular (MATERIAL): ng update @angular/material@14.2.7

Comando para atualizar tudo de uma única vez:
ng update @angular/cli@14.2.9 @angular/core@14.2.10 @angular/material@14.2.7 @angular/cdk@14.2.7

------------------------
-- ## 2 -PROJETO JAVA --
------------------------

-- Site para geração do projeto Spring Boot
link: https://start.spring.io/

-- Dependências Utilizadas
 - Spring Web
 - Spring Data JPA
 - SpringBoot Dev tools
 - Lombok
 - H2 Database

 -- Atualizando versao Spring
 Atualizar a versão (spring-boot-starter-parent) no pom do projeto.

 -- Validações BEan validation para JAVA EE ou Jakarta 
 link: https://beanvalidation.org/

 -- Contraints Bean Validation
 link: https://jakarta.ee/specifications/bean-validation/3.0/apidocs/

 -- Migração Sprimg Boot 3.0
 link: https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-3.0-Migration-Guide

-------------------------
-- ## 3 - COMANDOS NPM --
-------------------------

-- Instalar angular
-- No "cmd" após instalação do NodeJs, digitar o comando abaixo para instalar o Angular:
comando: npm install -g @angular/cli

-- Remover pasta node_modules
comando: rm -rf node-node_modules

-- Limpar o cache
comando: npm cache clean --force

-- Instalar as dependencias do projeto
comando: npm install --force

-- Atualizar as vulnerabilidades do projeto
npm audit fix

-- Buildar a aplicação
npm run build

-- Startar a aplicação
npm start {ou} npm run start
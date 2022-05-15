-- Comandos Curso Angula (LOIANE)

--------------------------------
-- ## REFERNCIAS DOCUMENTACAO --
--------------------------------
-- Docuemntação Angular
link: https://angular.io/

-- Angular Material
link: https://material.angular.io/

-----------------------------
-- ## 1 - COMANDOS ANGULAR --
-----------------------------

-- Instalar angular
-- No "cmd" após instalação do NodeJs, digitar o comando abaixo para instalar o Angular:
comando: npm install -g @angular/cli

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
comando: ng generate component <caminho\nome do component> ou ng g m <caminho\nome do component>
ex: ng g c courses\courses

# APIRest Administración de usuarios - NESUM

## Indiciones para reproducir app:
- Configurar Java v11 en la maquina local.
- Clonar proyecto en la maquina local.
- Abrir consola del sistema operativo y moverse a la carpeta raíz del proyecto, asi: 
![image](https://github.com/ca-milin15/nesum-users/assets/29680769/d5c06a87-5abe-4d68-ac45-ad9cb4e60db7)

### Ejecución de la aplicación
- ¡Importante! No es necesario ejecutar ningun script para configurar la DB, ya que Hibernate está configurado para crear y borrar DB cada vez que se inicia el proyecto, además, la capa de datos esta mapeado con Spring JPA, de manera que hace la migración de forma automática.
- Estando ubicado en la raíz del proyecto, ejecutar el siguiente comando: "mvn spring-boot:run" (Asegurarse que el puerto 8080 de la maquina esté desocupado), asi:
![image](https://github.com/ca-milin15/nesum-users/assets/29680769/72476386-4da2-4124-9d80-ab0d4c2ab544)
 - Estando el proyecto en ejecución a traves de las siguientes URL se puede acceder al dashboard de Swagger y H2 DB:
   - http://localhost:8080/api/swagger-ui.html
   - http://localhost:8080/api/h2-console

### Ejecución de pruebas unitarias e integración.
- Estando ubicado en la raíz del proyecto, ejecutar el siguiente comando: "mvn test", asi:
  ![image](https://github.com/ca-milin15/nesum-users/assets/29680769/ad3db126-6ece-4945-9433-ff4872ef997f)
  
- Al final de la ejecición verá la cantidad de test ejecutados, asi:
![image](https://github.com/ca-milin15/nesum-users/assets/29680769/3aec340a-3b88-4ece-8e7f-74f01be02e37)


## Diagramas de la solución: Modelo C4 - Administracion de usuarios.

### Diagrama de contexto
![1  C4DiagramaContexto](https://github.com/ca-milin15/nesum-users/assets/29680769/6349d355-2b09-492b-8a6b-19268fe8cf96)

### Diagrama de contenedores
![2  C4DiagramaContenedores](https://github.com/ca-milin15/nesum-users/assets/29680769/ec42d8dc-2512-43ab-9c20-9de3d4742326)

### Diagrama de componentes
![3  C4DiagramaComponentes](https://github.com/ca-milin15/nesum-users/assets/29680769/0f249299-3a2c-4324-a9ce-5049c6da5a00)

### Diagrama de código
![4  C4DiagramaCodigo](https://github.com/ca-milin15/nesum-users/assets/29680769/a96f41ac-1e4b-4a59-beb8-5e2942288ffa)

### Modelo relacional
![5  C4ModeloRelacional](https://github.com/ca-milin15/nesum-users/assets/29680769/1fe4e13d-8c4f-484d-9ab3-f704760e401b)


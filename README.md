# Alberto Arroyo Santofimia

HACK A BOSS

Prueba Técnica 1 - Java Básico. Desarrollo de una Aplicación de Gestión de Empleados con JPA

Al iniciar el programa despliega un menú del opciones.

0. **Menú de opciones inicial**

    ![image](https://github.com/user-attachments/assets/c5d438b0-a0cd-4e8b-9711-a58e9e784dfb)

1. **Agregar un nuevo empleado:** Los usuarios pueden añadir un nuevo empleado, incluyendo nombre, apellido, cargo, salario y fecha de inicio.

    ![image](https://github.com/user-attachments/assets/db8e1385-dbce-4a8f-b73e-ab7e079c48dc)
    
    ![image](https://github.com/user-attachments/assets/3d4a4f63-2964-4092-8f7d-5ecee12e0f0d)

 
2. **Listar empleados:** La aplicación permite ver la lista de todos los empleados en la base de datos o notifica que está vacía.

    ![image](https://github.com/user-attachments/assets/dfbeb56e-b4e6-4178-a597-d6b59384c341)
    
    ![image](https://github.com/user-attachments/assets/0f5f4a4b-6f59-4993-a902-3b15554819aa)

    Si la base de datos está vacía
    
    ![image](https://github.com/user-attachments/assets/4964883e-33fd-4324-927d-281c3c1f8f15)

3. **Actualizar información de un empleado:** Los usuarios pueden modificar la información de un empleado existente (nombre, apellido, cargo, salario o fecha de inicio).

    La aplicación nos pedirá en primer lugar el Id del empleado que se quiere editar, una vez elegido nos muestra los datos de ese usuario y muestra el menu de editar
    
    ![image](https://github.com/user-attachments/assets/e9deab9c-f7c6-4513-895d-59cff2e0de73)
    
    ![image](https://github.com/user-attachments/assets/de230cab-887f-4b5d-a555-d9f64d6621d1)
    
    ![image](https://github.com/user-attachments/assets/b1095dfb-f5a1-4d56-bc65-c0ba860ec517)
    
    ![image](https://github.com/user-attachments/assets/92aae99e-2105-4f5d-8c02-bb2073dd7ba0)
    
    ![image](https://github.com/user-attachments/assets/bc5fe31e-8f13-49a8-8d38-b1a4f582aac3)
    
    ![image](https://github.com/user-attachments/assets/df9a3d87-06e7-40fb-9ccf-39e94a189e51)
    
    Una vez que el usuario ha hecho las ediciones necesarias, tiene la opción de guardarlos en la base de datos y volver al menú principal o descartar todas las modificaciones y volver
    
    ![image](https://github.com/user-attachments/assets/0410d98d-76be-46a6-994f-0f06ef9bb8c5)
    
    ![image](https://github.com/user-attachments/assets/f2cf41ae-f419-4711-8473-4d09a3d541a7)
    
    ![image](https://github.com/user-attachments/assets/f3480132-2907-423b-9347-60488a31c37c)
    
    
    En caso de que se introduzca un Id de empleado que no exista, lo notifica y vuelve al menú principal
    
    ![image](https://github.com/user-attachments/assets/f78292ec-2b7a-4ded-bf08-71f166b6a52d)

    
4. **Eliminar un empleado:** La aplicación debe permitir eliminar un empleado de la base de datos.

    ![image](https://github.com/user-attachments/assets/91d228fb-4b4d-4fc4-8dc7-d22eb4bb42fb)
    
    Compración de si se ha borrado en la base de datos
    
    ![image](https://github.com/user-attachments/assets/0f166409-65a5-4759-9fb6-842e9ec09a51)
    
    
    En caso de que no exista el Id lo notifica por pantalla
    ![image](https://github.com/user-attachments/assets/204e8f50-beea-457d-a04d-4de66c5ec394)

5. **Buscar empleados por cargo:** Los usuarios deben poder buscar empleados por su cargo y ver una lista de los empleados que tienen ese cargo.

    ![image](https://github.com/user-attachments/assets/d085784e-67aa-48fa-b8a1-ff5e573daee4)
    
## **Requisitos técnicos:**

1. Se ha utilizado **Java** para el desarrollo de la aplicación.
    
3. **Estructuras de control** para la validación de datos de entrada, como asegurarte de que los campos obligatorios no estén vacíos (obligatorio) y que los valores sean válidos.

    En los menús de opciones no se pueden introducir ni valores fuera de rando ni caracteres no permitidos
   
    ![image](https://github.com/user-attachments/assets/9baee02a-fade-424d-8ba0-eeaeab9fc7aa)
    
    ![image](https://github.com/user-attachments/assets/932b692e-b1be-417a-a552-b1b523a14110)
    
    
    Metodos de validacion para entrada de datos de tipo Long, String, Double y Date. No se pueden introducir campos vacios ni contener espacios en blanco, ni caracteres no permitidos.
    
    ![image](https://github.com/user-attachments/assets/35aaab29-55ef-4bd8-8405-c98382e55ab4)
    
    ![image](https://github.com/user-attachments/assets/580e19d5-75fb-4df3-baf7-be53981c3f43)
    
    ![image](https://github.com/user-attachments/assets/b578eeec-6575-4fc3-bf17-164591deaa73)
    
    ![image](https://github.com/user-attachments/assets/3e05f514-70b3-438f-bab1-620754ec3cb9)

    
5. **JPA** para acceder a la **base de datos**.

    ![image](https://github.com/user-attachments/assets/f1a3b745-eb04-480d-b048-ea3d5f4c92b1)
    
6. List utilizadas para gestionar los datos antes de interactuar con la base de datos.

    ![image](https://github.com/user-attachments/assets/f7db190c-5028-4b0d-bebb-023e3b401d3b)
    
    ![image](https://github.com/user-attachments/assets/9446e53f-587a-49fe-9245-8c86a86c5cf9)

    

## **EXTRA**

En caso de que sepas manejar excepciones te proponemos el siguiente punto extra:

1. La aplicación debe ser capaz de manejar errores y excepciones de manera apropiada, como la gestión de registros duplicados o la búsqueda de empleados que no existen, etc.

    Método para comprobar si ya existe un usuario con ese nombre y apellido en la base de datos.
    
    ![image](https://github.com/user-attachments/assets/e3570ccd-d263-4b0b-9691-6d583914a124)
    
    Cuando se piden los datos se hace la comprobación y en caso de que exista no continua pidiendo los datos
    
    ![image](https://github.com/user-attachments/assets/1eb610b4-fd9f-4f83-9a8f-08207e461cd5)
    
    ![image](https://github.com/user-attachments/assets/9d8dbd38-59c4-415f-919d-df863159839f)
    
    Búsquedas que no existen:
    
    ![image](https://github.com/user-attachments/assets/578509ee-4d81-4a8f-ab0f-af9b2169e2f3)
    
    ![image](https://github.com/user-attachments/assets/287ed2be-724a-4183-b636-6d9ff6781d55)
    
    ![image](https://github.com/user-attachments/assets/481c184e-5979-488d-a3b4-c4f3845db58d)







# Alberto Arroyo Santofimia

HACK A BOSS

Prueba Técnica 1 - Java Básico. Desarrollo de una Aplicación de Gestión de Empleados con JPA


0. **Menú de opciones inicial**

![image](https://github.com/user-attachments/assets/c5d438b0-a0cd-4e8b-9711-a58e9e784dfb)

1. **Agregar un nuevo empleado:** Los usuarios pueden añadir un nuevo empleado, incluyendo nombre, apellido, cargo, salario y fecha de inicio.

![image](https://github.com/user-attachments/assets/db8e1385-dbce-4a8f-b73e-ab7e079c48dc)

![image](https://github.com/user-attachments/assets/3d4a4f63-2964-4092-8f7d-5ecee12e0f0d)

 
2. **Listar empleados:** La aplicación permite ver la lista de todos los empleados en la base de datos o notifica que está vacía.

![image](https://github.com/user-attachments/assets/424dbdc2-2def-4ecd-8fb0-df71ada90161)
    
3. **Actualizar información de un empleado:** Los usuarios deben poder modificar la información de un empleado existente (nombre, apellido, cargo, salario o fecha de inicio).

La aplicación nos pedirá en primer lugar el Id del empleado que se quiere editar, una vez elegido nos muestra los datos de ese usuario y muestra el menu de editar

![image](https://github.com/user-attachments/assets/36dcd6d1-99a4-4e80-bedd-4433b0b4e441)
![image](https://github.com/user-attachments/assets/51f9a953-9f37-446a-983e-acd0779d608e)
![image](https://github.com/user-attachments/assets/4f8821f1-fdf2-433d-aa3c-d0c66c696179)




En caso de que se introduzca un Id de empleado que no exista no notifica y vuelve al menú principal

![image](https://github.com/user-attachments/assets/f78292ec-2b7a-4ded-bf08-71f166b6a52d)

    
4. **Eliminar un empleado:** La aplicación debe permitir eliminar un empleado de la base de datos.
    
5. **Buscar empleados por cargo:** Los usuarios deben poder buscar empleados por su cargo y ver una lista de los empleados que tienen ese cargo.
    

## **Requisitos técnicos:**

1. Utiliza **Java** para el desarrollo de la aplicación.
    
2. Debes utilizar **estructuras de control** para la validación de datos de entrada, como asegurarte de que los campos obligatorios no estén vacíos (obligatorio) y que los valores sean válidos (opcional).
    
3. La aplicación debe utilizar **JPA** para acceder a la **base de datos**. Asegúrate de configurar una fuente de datos y una entidad "Empleado" con las propiedades necesarias.
    
4. Utiliza **colecciones** para gestionar los datos antes de interactuar con la base de datos.
    

## **EXTRA**

En caso de que sepas manejar excepciones te proponemos el siguiente punto extra:

1. La aplicación debe ser capaz de manejar errores y excepciones de manera apropiada, como la gestión de registros duplicados o la búsqueda de empleados que no existen, etc.
    

## **Entregables**

- **Código fuente** de la aplicación en un repositorio de control de versiones (GitHub).
    
    - Utilizar como formato de **nombre del repositori**o la siguiente combinación: apellido+nombre+_pruebatec1. Ej: **dePaulaLuisina_pruebatec1**
        
- Un informe o documentación breve que describa cómo ejecutar y probar la aplicación como así también los "SUPUESTOS" que pudieron ser considerados. Esto puede ser incluido en el archivo **README** de **GitHub**.
    
- El nombre de la base de datos debe ser **empleados**
    
- Archivo **.sql** de la base de datos
    
- Fecha de entrega máxima **Jueves dd/mm/yyyy** a las **xx hs (mediodía)**

 project_spring_boot

endpoints:
* GET /api/companys 
* POST /api/companys
* GET /api/companys/{id}
* PUT /api/companys/{id}
* DELETE /api/companys/{id}
* POST /api/companys/{id}/employees

No funcionaba encontrar la empresa por id, el put de actualizar empresa y delete porque había sobre escrito el método de findById    D:
borré ese método sobre esscrito y ahora funcionan, adjunto pruebas.
Ahora el problema es crear al empleado, aunque mejor hubiera hecho una que solo era agregarlo a la empresa

BUILD

<img width="1877" height="850" alt="image" src="https://github.com/user-attachments/assets/4b242459-3972-4ab5-bbfa-4c9125e1fc88" />



BOOTRUN

<img width="1885" height="880" alt="image" src="https://github.com/user-attachments/assets/5e47acbf-7e39-4c06-baf4-36fc8d6e2483" />



GRADLEW TEST
<img width="1878" height="862" alt="image" src="https://github.com/user-attachments/assets/e58ccdd8-f89f-40e1-a8d1-b0af6d7a4234" />



OBTENER TODAS LAS EMPRESAS GET /api/companys 
<img width="1835" height="857" alt="image" src="https://github.com/user-attachments/assets/c9256ff4-b2bd-4e9b-a812-e479db8f7758" />


CREAR UNA EMPRESA POST /api/companys
<img width="1812" height="825" alt="image" src="https://github.com/user-attachments/assets/59360f43-7618-4381-bd3b-5fb46b3eb26e" />


ELIMINAR UNA EMPRESA DELETE /api/companys/{id}
<img width="1787" height="758" alt="image" src="https://github.com/user-attachments/assets/d7dd2f57-8f58-452a-8e4d-44a9142859e6" />

OBTENER UNA EMPRESA POR ID GET /api/companys/{id}
<img width="1797" height="827" alt="image" src="https://github.com/user-attachments/assets/878af0f5-e5d3-4f14-af88-57840600c892" />

ACTUALIZAR EMPRESA PUT /api/companys/{id}
<img width="1826" height="921" alt="image" src="https://github.com/user-attachments/assets/6361d2da-f706-4475-be54-a9f0b8a26377" />



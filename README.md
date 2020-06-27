# Mini Amazon Go ![Docker CI](https://github.com/Cavaleiros-do-Xunxo/mazgo-api/workflows/Docker%20CI/badge.svg)

##### Stock Control using Computer Vision.

This is the backend service that controls the stock as well as provides the RESTful interface for the frontpage. 

Paths:
```
History:
    GET /history
    GET /history/{id}

Product:
    GET /products
    GET /products/{id}
    GET /products/{id}/history
    POST /products
    PATCH /products
```

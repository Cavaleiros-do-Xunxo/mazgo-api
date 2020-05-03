# Mini Amazon Go [![Build Status](https://travis-ci.com/Cavaleiros-do-Xunxo/mazgo-api.svg?branch=master)](https://travis-ci.com/Cavaleiros-do-Xunxo/mazgo-api)

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
    PATCH /products/{idf}
```

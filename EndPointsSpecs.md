# API Endpoints Specifications

## 1. Check Balance

**Endpoint:** `POST /balance`

**Request Body:**

```json
{
  "token": "BOE-0112-XgF0"
}
```

**Description:** Retrieves the current balance of the bank.

**Response:**

- `200 OK`: Current balance of bank is sufficient
- `401 Unauthorized Access`: Indicates that the token entered is invalid.

```json
// Example: 200 OK
{
  "code": 200,
  "message": "OK",
  "data": {
    "balance": "100000"
  }
}
```
```json
// Example: 401 Unauthorized Access
{
  "code": 401,
  "message": "Unauthorized Access",
  "data": null
}
```

## 2. Deposit Money

**Endpoint:** `POST /deposit`

**Request Parameters:** 
```json
{
  "token": "BOE-0112-XgF0",
  "amount": "10000"
}
```

**Description:** Deposits the specified amount of money into the bank's account.

**Response:**

- `200 OK`: Indicates that the balance has been added.
- `401 Unauthorized Access`: Indicates that the token entered is invalid.
- `403 Bad Request`: Indicates that the amount of money is too small.
```json
// Example: 200 OK
{
  "code": 200,
  "message": "OK",
  "data": null
}
```
```json
// Example: 401 Unauthorized Access
{
  "code": 401,
  "message": "Unauthorized Access",
  "data": null
}
```
```json
// Example: 403 Bad Request
{
  "code": 403,
  "message": "Bad Request",
  "data": null
}
```

## 3. Withdraw Money

**Endpoint:** `POST /withdraw`

**Request Parameters:** 
```json
{
  "token": "BOE-0112-XgF0",
  "amount": "10000"
}
```

**Description:** Withdraws the specified amount of money from bank's account.

**Response:**

- `200 OK`: Indicates that the balance has been added.
- `401 Unauthorized Access`: Indicates that the token entered is invalid.
- `403 Bad Request`: Indicates that the amount of money is not allowed.
```json
// Example: 200 OK
{
  "code": 200,
  "message": "OK",
  "data": null
}
```
```json
// Example: 401 Unauthorized Access
{
  "code": 401,
  "message": "Unauthorized Access",
  "data": null
}
```
```json
// Example: 403 Exceeded the Maximum Amount
{
  "code": 403,
  "message": "Exceeded the Maximum Amount",
  "data": null
}
```
```json
// Example: 404 Insufficient Funds
{
  "code": 404,
  "message": "Insufficient Funds",
  "data": null
}
```
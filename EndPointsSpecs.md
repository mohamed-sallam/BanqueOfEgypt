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
- `401 Unauthorized Access`: Indicates that the data entered is invalid.

```json
// Example: 200 OK
{
  "code": 200,
  "message": "Success",
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

- `200 OK`: Indicates that the balance has been added .
- `99993 Invalid Data`: Indicates that the data entered is invalid.

## 3. Withdraw Money

**Endpoint:** `/withdrawMoney`

**Request Type:** `GET`

**Request Parameters:** `amount`

**Description:** Withdraws the specified amount of money from bank's account.

**Response:**

- `200 OK`: Indicates that the balance has been deducted.
- `99993 Invalid Data`: Indicates that the data entered is invalid.
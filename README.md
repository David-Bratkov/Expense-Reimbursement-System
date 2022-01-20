# Expense Reimbursement System

## Project Description

The Expense Reimbursement System will manage the process of reimbursing employees for expenses incurred while on company time. All employees in the company can login and submit requests for reimbursement and view their past tickets and pending requests. Finance managers can log in and view all reimbursement requests and past history for all employees in the company. Finance managers are authorized to approve and deny requests for expense reimbursement.

## Technologies Used

* HTML
* CSS
* JavaScript
* Java
* PostgreSQL
* AWS RDS
* AWS S3
* InteliJ

## Getting Started
   
git clone https://github.com/David-Bratkov/Expense-Reimbursement-System.git

open up the backend in InteliJ start running Main

open up selenium with another window in InteliJ and make sure the backend is running first before running Main

## Enviroment Variables Setup

> There are 3 enviroment variables used in this project

> AWS_RDS_ENDPOINT - The end point needed to connect to the database
> RDS_USERNAME - The username needed to log into the database
> RDS_PASSWORD - The password assosiated with the username for the database

## PostgreSQL DBever Database Script Commands:
```
CREATE TABLE ERS_REIMBURSEMENT (
	REIMB_ID serial PRIMARY KEY NOT NULL,
	REIMB_AMOUNT decimal(30, 2) NOT NULL,
	REIMB_SUBMITTED Timestamp NOT NULL DEFAULT current_timestamp,
	REIMB_RESOLVED Timestamp DEFAULT current_timestamp,
	REIMB_DESCRIPTION varchar(250),
	REIMB_RECEIPT bytea,
	REIMB_AUTHOR int NOT NULL,
	REIMB_RESOLVER int,
	REIMB_STATUS_ID int NOT NULL,
	REIMB_TYPE_ID int NOT NULL,
	FOREIGN KEY (REIMB_AUTHOR) REFERENCES ERS_USERS(ERS_USERS_ID) ON DELETE CASCADE,
	FOREIGN KEY (REIMB_RESOLVER) REFERENCES ERS_USERS(ERS_USERS_ID) ON DELETE CASCADE,
	FOREIGN KEY (REIMB_STATUS_ID) REFERENCES ERS_REIMBURSEMENT_STATUS(REIMB_STATUS_ID) ON DELETE CASCADE,
	FOREIGN KEY (REIMB_TYPE_ID) REFERENCES ERS_REIMBURSEMENT_TYPE(REIMB_TYPE_ID) ON DELETE CASCADE
);
```
```
CREATE TABLE ERS_REIMBURSEMENT_STATUS (
	REIMB_STATUS_ID serial PRIMARY KEY,
	REIMB_STATUS varchar(10) NOT null
);
```
```
CREATE TABLE ERS_REIMBURSEMENT_TYPE (
	REIMB_TYPE_ID serial PRIMARY KEY,
	REIMB_TYPE varchar(10) NOT NULL
);
```
```
CREATE TABLE ERS_USERS (
	ERS_USERS_ID serial PRIMARY KEY,
	ERS_USERNAME varchar(50) UNIQUE NOT NULL,
	ERS_PASSWORD varchar(50) NOT NULL,
	USER_FIRST_NAME varchar(100) NOT NULL,
	USER_LAST_NAME varchar(100) NOT NULL,
	USER_EMAIL varchar(150) UNIQUE NOT NULL,
	USER_ROLE_ID int NOT NULL,
	FOREIGN KEY (USER_ROLE_ID) REFERENCES ERS_USER_ROLES(ERS_USER_ROLE_ID) ON DELETE CASCADE
);
```
```
CREATE TABLE ERS_USER_ROLES (
	ERS_USER_ROLE_ID serial PRIMARY KEY,
	USER_ROLE varchar(10) NOT NULL
);
```
```
INSERT INTO ERS_USER_ROLES VALUES (DEFAULT, 'Employee');
INSERT INTO ERS_USER_ROLES VALUES (DEFAULT, 'Manager');
INSERT INTO ERS_USERS VALUES (DEFAULT, 'JoeBob', 'TestPassword', 'Joe', 'bob', 'joebob@joebob.com', 1);
INSERT INTO ERS_USERS VALUES (DEFAULT, 'BigNerd', 'TestPassword2', 'Manager', 'Nerd', 'Nerdbob@Nerdbob.com', 2);
INSERT INTO ERS_USERS VALUES (DEFAULT, 'SmallNerd', 'TestPassword3', 'Bin', 'Who', 'BinWho@BinWho.com', 1);
INSERT INTO ERS_USERS VALUES (DEFAULT, 'Jim Pickens', 'CheeseLover3', 'Jim', 'Pickens', 'JimPickens@Seagulls.com', 1);
INSERT INTO ers_reimbursement_type VALUES (DEFAULT, 'LODGING');
INSERT INTO ers_reimbursement_type VALUES (DEFAULT, 'TRAVEL');
INSERT INTO ers_reimbursement_type VALUES (DEFAULT, 'FOOD'); 
INSERT INTO ers_reimbursement_type VALUES (DEFAULT, 'OTHER');
INSERT INTO ers_reimbursement_status VALUES (DEFAULT, 'Pending');
INSERT INTO ers_reimbursement_status VALUES (DEFAULT, 'Accepted');
INSERT INTO ers_reimbursement_status VALUES (DEFAULT, 'Rejected');
```

# SQL Practice
There is a high chance you will be asked to write a SQL query. The chinook database is a practice database. Run the script here in your DB to get it.
https://raw.githubusercontent.com/lerocha/chinook-database/master/ChinookDatabase/DataSources/Chinook_PostgreSql.sql
- Querying practice
    - Select all records from the Employee table.
        - select * from Employee;
    - Select all records from the Employee table where last name is King.
        - select * from "Employee" where "LastName" = 'King';
    - Select all records from the Employee table where first name is Andrew and REPORTSTO is NULL.
        - select * from "Employee"
            where "FirstName" = 'Andrew' and "ReportsTo" is null;
    -  Select all albums in Album table and sort result set in descending order by title.
        - select * from "Album"
            order by "Title" desc;
    - Select first name from Customer and sort result set in ascending order by city
        - select "FirstName" from "Customer"
            order by "City";
    - Select all invoices with a billing address like “T%”
        - select * from "Invoice"
            where "BillingAddress" like 'T%';
    - Select all invoices that have a total between 15 and 50
        - select * from "Invoice"
            where "Total" between 15 and 50;
    - Select all employees hired between 1st of June 2003 and 1st of March 2004
        - select * from "Employee"
            where "HireDate" between '2003/06/01' and '2004/03/01';
- Insert practice
    - Insert two new records into Genre table
        - insert into "Genre" ("GenreId", "Name") values (26, 'Folk');
        - insert into "Genre" ("GenreId", "Name") values (27, 'Neue Deutsche Hart');
    - Insert two new records into Employee table
        - insert into "Employee" (
	            "EmployeeId", "Address", "BirthDate", "City", "Country", "Email", "Fax", "FirstName", "HireDate", "LastName", "Phone", "PostalCode", "ReportsTo", "State", "Title"
            ) values (
	            9, '123 Fake St.', '2020/09/01', 'Fake Town', 'Fake Country', 'fake@email.com',	null, 'Fake', '2020/09/02', 'Name', '1234567890', '12345', 1, 'Fake', 'Assistant to the GM'
            );
        - insert into "Employee" (
                "EmployeeId", "Address", "BirthDate", "City", "Country", "Email", "Fax", 
                "FirstName", "HireDate", "LastName", "Phone", "PostalCode", "ReportsTo", 
                "State", "Title"
          ) values (
                10, '134 Fake Rd.', '1920/08/02', 'Fake City', 'Fake Country', 'also_fake@email.com', null,
                'Another', '1902/07/03', 'Faker', '0987654321', '54321', 9, 'Fake', 'Assistant to the Assistant GM'
          );
        - 
    - Insert two new records into Customer table
        - insert into "Customer" ( 
	        "CustomerId", "Address", "City", "Company", "Country", "Email", "Fax", "FirstName", "LastName", "Phone", "PostalCode", "State", "SupportRepId"
          ) values ( 
	        60, '123 Fake Blvd.', 'Fake City', 'Fkr', 'Fake Country', 'faker@fkr.com', null, 'Faker', 'Also', '3216540977', '32145', 'Fake State', 4
          );
        - insert into "Customer" ( 
	        "CustomerId", "Address", "City", "Company", "Country", "Email", "Fax", "FirstName", "LastName", "Phone", "PostalCode", "State", "SupportRepId"
          ) values ( 
	        61, '321 Fake Cir.', 'Fake Town', 'Fkr', 'Fake Country', 'another@fkr.com', null, 'Another', 'Faker', '6541238790', '21354', 'Fake State', 4
          );
          
- Update Practice
    - Update Aaron Mitchell in Customer table to Robert Walter
        - update "Customer" set "FirstName" = 'Robert', "LastName" =     'Walter' where "CustomerId" =32;
    - Update name of artist in the Artist table “Creedence Clearwater Revival” to “CCR”
        - update "Artist" set "Name" = 'CCR' where "ArtistId" = 76;
- Joins
    - Create an inner join that joins customers and orders and specifies the name of the customer and the invoiceId
        - select CONCAT(c."FirstName", ' ', c."LastName") as "Customer Name", i."InvoiceId"
          from "Customer" c inner join "Invoice" i on c."CustomerId"  = i."CustomerId";
    - Create an outer join that joins the customer and invoice table, specifying the CustomerId, firstname, lastname, invoiceId, and total.
        - select c."CustomerId", c."FirstName", c."LastName", i."InvoiceId", i."Total"
          from "Customer" c full outer join "Invoice" i on c."CustomerId" = i."CustomerId";
    - Create a right join that joins album and artist specifying artist name and title.
        - select aa."Name", a."Title" 
            from "Album" a right join "Artist"aa on a."ArtistId" = aa."ArtistId";
    - Create a cross join that joins album and artist and sorts by artist name in ascending order.
        - select * from "Album" a cross join "Artist" aa order by aa."ArtistId";
    - Perform a self-join on the employee table, joining on the reportsto column.
        - select e."FirstName", e."LastName", m."FirstName", m."LastName" from "Employee" e left join "Employee" m on e."ReportsTo" = m."EmployeeId";
- Set Operators
    - Use a unionall to create a table that has the name of all employees and customers
        - select CONCAT(e."FirstName", ' ', e."LastName") as "Name"
            from "Employee" e
            union
          select CONCAT(c."FirstName", ' ', c."LastName") as "Name"
            from "Customer" c
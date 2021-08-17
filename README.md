# CONTACTS

Here is something people probably use regularly: contacts. This program can create contacts (like on the mobile phone) and search for people or organizations by name.

In the app, you can store phone numbers of different companies, like your favorite pizza shop or your bank. These organizations don't have a name or a surname; they have an organization name and an address.

Additionally, a person can have a birth date and gender, but a company can't have that.

Used inheritance to solve the issue.

## Example

Below is an example of how your output might look. The symbol > represents the user input.

    open phonebook.db

    [menu] Enter action (add, list, search, count, exit): > count
    The Phone Book has 6 records.

    [menu] Enter action (add, list, search, count, exit): > search
    Enter search query: > cent
    Found 3 results:
    1.Central Bank
    2.Centurion Adams
    3.Decent Pizza Shop

    [search] Enter action ([number], back, again): > again
    Enter search query: > shop
    Found 2 results:
    1.Decent Pizza Shop
    2.Car shop

    [search] Enter action ([number], back, again): > 2
    Organization name: Car shop
    Address: Wall St. 3
    Number: +0 (123) 456-789-9999
    Time created: 2018-01-01T00:03
    Time last edit: 2018-04-29T11:34

    [record] Enter action (edit, delete, menu): > edit
    Select a field (name, address, number): > name
    Enter name: > New Car Shop
    Saved
    Organization name: New Car Shop
    Address: Wall St. 3
    Number: +0 (123) 456-789-9999
    Time created: 2018-01-01T00:03
    Time last edit: 2018-11-20T11:04

    [record] Enter action (edit, delete, menu): > menu

    [menu] Enter action (add, list, search, count, exit): > search
    Enter search query: > new
    Found 1 result:
    1.New Car Shop

    [search] Enter action ([number], back, again): > back

    [menu] Enter action (add, list, search, count, exit): > list
    1.New Car Shop
    2. Decent Pizza Shop
    3. Central Bank
    4. Centurion Adams
    5. John Smith
    6. Alice Wonderlanded

    [list] Enter action ([number], back): > 6
    Name: Alice
    Surname: Wonderlanded
    Birth date: [no data]
    Gender: F
    Number: +123123 (123) 12-23-34-45
    Time created: 2018-03-12T11:21
    Time last edit: 2018-03-12T11:21

    [record] Enter action (edit, delete, menu): > edit
    Select a field (name, surname, birth, gender, number): > number
    Enter number: > +23 (321) 12-12 12 12
    Saved
    Name: Alice
    Surname: Wonderlanded
    Birth date: [no data]
    Gender: F
    Number: +23 (321) 12-12 12 12
    Time created: 2018-03-12T11:21
    Time last edit: 2018-11-20T11:07

    [record] Enter action (edit, delete, menu): > menu

    [menu] Enter action (add, list, search, count, exit): > exit
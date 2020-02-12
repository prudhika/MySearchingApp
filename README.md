# SearchingApp

This application will give you ability to search attributes across Users, Tickets and Organizations. 

## Getting Started

These instructions will get you a copy of the project up and running app on your local machine.

After cloning/downloading this project- Go to the projet directory

```
mvn compile
mvn test
mvn install
```
Run the app -

```
mvn exec:java -Dexec.mainClass=searchApp.SearchApp
```

### Prerequisites


```
java 8, maven
```

### Steps to use search 

A step by step series of examples that tell you how to search

### Select Category


```
Select 1) Users or 2)Tickets or 3) Organizations 
```

Enter 1 - to search users.

USER fields:
```
[shared, last_login_at, role, signature, timezone, verified, created_at, active, external_id, locale, url, suspended, tags, phone, name, alias, _id, email]
```
Enter 2 - to search tickets.

TICKET fields:
```
[subject, created_at, description, submitter_id, external_id, type, priority, url, tags, via, organization_id, due_at, _id, has_incidents, status, assignee_id]
```
Enter 3 - to search oraganizations.

ORGANIZATION fields:
```
[shared_tickets, name, created_at, external_id, details, _id, url, domain_names, tags]
```
### Input Field
```
Please Enter search Item 
_id
```
### Input value
```
Please Enter search Value 
1
```

### Result
```
shared                                            false
last_login_at                                     2013-08-04T01:03:27 -10:00
role                                              admin
signature                                         Don't Worry Be Happy!
timezone                                          Sri Lanka
verified                                          true
created_at                                        2016-04-15T05:19:46 -10:00
active                                            true
external_id                                       74341f74-9c79-49d5-9611-87ef9b6eb75f
locale                                            en-AU
url                                               http://initech.zendesk.com/api/v2/users/1.json
suspended                                         true
tags                                              ["Springville","Sutton","Hartsville\/Hartley","Diaperville"]
phone                                             8335-422-718
organization_id                                   119
-----------------------------------------------------------

```

### No Result

```
No Results Found - 
```


### And repeat

```
 Do you want to do a new search ? 1) YES or Any other key: NO 
```




# 07-vet
[![Build Status](https://travis-ci.com/seqre/veterinaryclinic.svg?token=JmUSxPrpzx14kkoZrVNF&branch=master)](https://travis-ci.com/seqre/veterinaryclinic)

## Specification
You should create a service to register vet visits.

##### The service should provide (functional requirements):
* List of **clients** (persons), with attributes:
    * first name,
    * last name,
    * home address,
    * contact (email and/or telephone number).
* List of **patients** (animals), with attributes:
    * name,
    * species,
    * date of birth (note: may be inaccurate, e.g. only year and month, or year itself!),
    * date of departure of the poor animal to the other world
* The ability to associate (and unbind) patients with clients.
* **Visits**. The visit can be arranged only for the future from the next day (not for the same day), from 8:00 to 20:00. Two visits cannot overlap in time; if you try to create two visits covering the same time, you must return HTTP status 409: conflict (with description). Visits attributes:
    * patient,
    * start time,
    * duration (possible values: 15/30/45/60 minutes),
    * description (completed during the visit - editing),
    * status (possible values: appointment, visit done, the patient did not come)  
* You should be able to display all visits of a given patient, a given client, all visits of a given day, etc. - what else you find useful.

##### Technical requirements:
* Service API should be REST, coding: UTF-8, format: JSON.
* The API should be designed according to RESTful good practice (https://en.wikipedia.org/wiki/Representational_state_transfer).
* Framework spring is very strongly recommended, 
* Unit tests with test report and coverage,
* Pipeline in Gitlab, which the application will compile, test, and implement on Herok
* Database - PostgreSQL in Heroku
* Take care of proper, nicely formatted login and injection of dependencies.


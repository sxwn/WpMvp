// IWpPerson.aidl
package com.xiaowei.wpmvp;

// Declare any non-default types here with import statements

interface IWpPerson {
   void addPerson(Person p);
   List<Person> getPersons();
}

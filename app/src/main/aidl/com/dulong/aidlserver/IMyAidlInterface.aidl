// IMyAidlInterface.aidl
package com.dulong.aidlserver;
// Declare any non-default types here with import statements
import com.dulong.aidlserver.Person;

interface IMyAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString);
   // void setPerson(Person person);
   int add (int a,int b);
   void setPerson(in Person person);
}

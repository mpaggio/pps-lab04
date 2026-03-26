package it.unibo.pps.tasks.adts

import org.junit.Test
import org.junit.Assert.*

import it.unibo.pps.u03.extensionmethods.Sequences.Sequence, Sequence.*

class SchoolModelTest:

  import SchoolModel.BasicSchoolModule.*
  val school: School = emptySchool

  @Test def testInitiallyEmpty(): Unit =
    assertEquals(Nil(), school.teachers)
    assertEquals(Nil(), school.courses)
    assertFalse(school.hasTeacher("John"))
    assertFalse(school.hasCourse("Math"))

  @Test def testInsertingTeachersAndCourses(): Unit =
    val john = teacher("John")
    val math = course("Math")
    val italian = course("Italian")
    val school2 = school.setTeacherToCourse(john, math)
    assertEquals(Cons("John", Nil()), school2.teachers)
    assertEquals(Cons("Math", Nil()), school2.courses)
    assertTrue(school2.hasTeacher("John"))
    assertTrue(school2.hasCourse("Math"))
    assertFalse(school2.hasCourse("Italian"))
    val school3 = school2.setTeacherToCourse(john, italian)
    assertEquals(Cons("John", Nil()), school3.teachers)
    assertEquals(Cons("Italian", Cons("Math", Nil())), school3.courses)
    assertTrue(school3.hasTeacher("John"))
    assertTrue(school3.hasCourse("Math"))
    assertTrue(school3.hasCourse("Italian"))
    assertEquals(Cons("Italian", Cons("Math", Nil())), school3.coursesOfATeacher(john))

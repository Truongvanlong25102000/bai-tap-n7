package com.example.btn7;

public interface IStudentQueryHandle {
    void restoreDataToUI();

    void removeStudentById(long id);

    void updateStudentById(long id,String name,String mClass);
}

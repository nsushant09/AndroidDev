package com.neupanesushant.imstask.data.model

fun Employee.toIndi(): IndiEmployee {
    return IndiEmployee(
        this.id,
        this.employee_name,
        this.employee_salary,
        this.employee_age,
        this.profile_image
    )
}
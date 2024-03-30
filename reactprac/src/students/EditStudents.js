import React, {useEffect, useState} from "react";
import axios from "axios";
import {Link, useNavigate, useParams} from "react-router-dom";

export default function EditStudents() {
    let navigate=useNavigate()

    const {id} = useParams()

    const [student, setStudent]=useState({
        idUcznia:"",
        name:"",
        birthDate:""
    })
    const{idUcznia, name, birthDate}=student

    const onIdChange = (e) => {
        setStudent({ ...student, idUcznia: e.target.value });
    };

    const onNameChange = (e) => {
        setStudent({ ...student, name: e.target.value });
    };

    const onBirthdayChange = (e) => {
        setStudent({ ...student, birthDate: e.target.value });
    };

    useEffect(() => {
        loadStudent()
    }, []);
    const onSubmit=async (e)=>{
        e.preventDefault();
        await axios.put(` http://localhost:8080/api/uczniowie/id`, student)
        navigate("/")
    }

    const loadStudent = async (e) => {
        const result = await axios.get(`http://localhost:8080/api/uczniowie/id/${id}`)
        setStudent(result.data)
    }

    return <div className="container">
        <div className="row">
            <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
                <h2 className="text-center m-4">Edit student</h2>
                <form onSubmit={(e)=>onSubmit(e)}>
                    <div className="mb-3">
                        <label htmlFor="idUcznia" className="form-label">
                            idUcznia
                        </label>
                        <input type="text" className={"form-control"} placeholder={"Enter idUcznia"} id={"idUcznia"} value={idUcznia} onChange={(e)=>onIdChange(e)}></input>
                    </div>
                    <div className="mb-3">
                        <label htmlFor="name" className="form-label">
                            Name
                        </label>
                        <input type="text" className={"form-control"} placeholder={"Enter name"} name={"name"} value={name} onChange={(e)=>onNameChange(e)}></input>
                    </div>
                    <div className="mb-3">
                        <label htmlFor="birthDate" className="form-label">
                            BirthDate
                        </label>
                        <input type="text" className={"form-control"} placeholder={"Enter birthDate"} value={birthDate ? birthDate.toString() : ""} onChange={(e)=>onBirthdayChange(e)}></input>
                    </div>
                    <button type="submit" className="btn btn-outline-primary">
                        Submit
                    </button>
                    <Link className="btn btn-outline-danger mx-2" to={"/"}>
                        Cancel
                    </Link>
                </form>
            </div>

        </div>
    </div>
}
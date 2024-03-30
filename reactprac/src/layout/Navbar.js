import React from "react";
import 'bootstrap/dist/css/bootstrap.min.css';
import {Link} from "react-router-dom";

export default function Navbar(){
    return(
        <div>
            <nav className="navbar navbar-expand-lg bg-body-tertiary">
                <div className="container-fluid">
                    <a className="navbar-brand" href="#">School Documentation</a>
                    <Link className="btn btn-outline-dark" to="/addstudent">Add Student</Link>
                </div>
            </nav>

        </div>
    )
}
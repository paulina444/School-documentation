import React, { useEffect, useState } from "react";
import axios from "axios";
import { Link, useParams } from "react-router-dom";
import { format } from "date-fns";
import { Pagination } from "react-bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";

export default function Home() {
    const [students, setStudents] = useState([]);
    const { id } = useParams();
    const [currentPage, setCurrentPage] = useState(1);
    const [studentsPerPage] = useState(6);
    const [totalPages, setTotalPages] = useState(1);

    useEffect(() => {
        loadStudents();
    }, [currentPage]);

    const loadStudents = async () => {
        const result = await axios.get(`http://localhost:8080/api/students/stronnicowanie?page=${currentPage}&size=${studentsPerPage}`);
        setStudents(result.data.content);
        setTotalPages(result.data.totalPages);
    };

    const deleteStudents = async (id) => {
        await axios.delete(`http://localhost:8080/api/uczniowie/${id}`);
        loadStudents();
    };

    const handlePageChange = (page) => {
        setCurrentPage(page);
    };

    return (
        <div className="container">
            <div className="py-4">
                <table className="table border">
                    <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">id</th>
                        <th scope="col">name</th>
                        <th scope="col">birthDate</th>
                    </tr>
                    </thead>
                    <tbody>
                    {students.map((student, index) => (
                        <tr key={index}>
                            <th scope="row">{index + 1}</th>
                            <td>{student.idUcznia}</td>
                            <td>{student.name}</td>
                            <td>{format(new Date(student.birthDate), "yyyy-MM-dd")}</td>
                            <td>
                                <Link
                                    className="btn btn-primary mx-2"
                                    to={`/editstudent/${student.id}`}
                                >
                                    Edit
                                </Link>
                                <button
                                    className="btn btn-primary mx-2"
                                    onClick={() => deleteStudents(student.id)}
                                >
                                    Delete
                                </button>
                            </td>
                        </tr>
                    ))}
                    </tbody>
                </table>
                <Pagination>
                    {Array.from({ length: totalPages }).map((_, index) => (
                        <Pagination.Item
                            key={index}
                            active={index + 1 === currentPage}
                            onClick={() => handlePageChange(index + 1)}
                        >
                            {index + 1}
                        </Pagination.Item>
                    ))}
                </Pagination>
            </div>
        </div>
    );
}

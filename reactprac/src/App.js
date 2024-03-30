
import './App.css';
import "../node_modules/bootstrap/dist/css/bootstrap-grid.min.css"
import Navbar from "./layout/Navbar";
import Home from "./pages/Home";
import {BrowserRouter as Router, Routes, Route} from "react-router-dom";
import AddStudents from "./students/AddStudents";
import EditStudents from "./students/EditStudents";


function App() {
  return (
    <div className="App">
    <Router>
        <Navbar></Navbar>
        <Routes>
            <Route exact path="/" element={<Home></Home>}></Route>
            <Route exact path="/addstudent" element={<AddStudents></AddStudents>}></Route>
            <Route exact path="/editstudent/:id" element={<EditStudents></EditStudents>}></Route>
        </Routes>
    </Router>
    </div>
  );
}

export default App;





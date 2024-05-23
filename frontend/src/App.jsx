import './App.css'
import MainPage from "./pages/MainPage.jsx";
import {Route, Routes} from "react-router-dom";
import {RequireAuth} from "./auth/RequireAuth.jsx";
import RegisterForm from "./components/auth/register/RegisterForm.jsx";
import LoginForm from "./components/auth/login/LoginForm.jsx";

function App() {

    return (
        <Routes>
            <Route index path='/' element={<MainPage/>}/>
            <Route path='/login' element={<LoginForm/>}/>
            <Route path='/register' element={<RegisterForm/>}/>
        </Routes>
    )
}

export default App

import './App.css'
import MainPage from "./pages/MainPage.jsx";
import OrderHistoryPage from "./pages/OrderHistoryPage.jsx";
import CheckoutPage from "./pages/checkout/CheckoutPage.jsx"
import {Route, Routes} from "react-router-dom";
import {RequireAuth} from "./auth/RequireAuth.jsx";
import RegisterForm from "./components/auth/register/RegisterForm.jsx";
import LoginForm from "./components/auth/login/LoginForm.jsx";
//import OrderHistory from "./components/orderhistory/OrderHistory.jsx";

function App() {
    return (
        <Routes>
            <Route index path='/' element={<MainPage/>}/>
            <Route path='/login' element={<LoginForm/>}/>
            <Route path='/register' element={<RegisterForm/>}/>
            <Route path='/order-history' element={<OrderHistoryPage/>}/>
            <Route path='/checkout' element={<CheckoutPage/>}/>
        </Routes>
    );
}

export default App;
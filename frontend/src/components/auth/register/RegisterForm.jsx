import {useState} from 'react';
import {Link, useNavigate} from 'react-router-dom';
import "../AuthForm.css"
import validate from "../utils/validate.jsx";
import api from "../../../services/api.jsx";

const Register = () => {
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [repeatPassword, setRepeatPassword] = useState('');
    const [registrationError] = useState('');
    const [repeatPasswordError, setRepeatPasswordError] = useState('');
    const navigate = useNavigate();

    const handleRegister = async (e) => {
        e.preventDefault();

        if (repeatPassword !== password) {
            setRepeatPasswordError('Passwords do not match.');
            return;
        } else {
            setRepeatPasswordError('');
        }

        if (!validate(password)) return

        const userData = {
            firstName,
            lastName,
            email,
            password,
        };

        try {
            const response = await api.registerUser(userData)
            alert(response.data.message);
            if (response.data.success === true) {
                navigate("/login");
            }
        } catch (error) {
            console.error('Error during registration:', error);
        }
    };

    return (
        <div className="container">
            <div className="col-4">
                <div className="card">
                    <div className="card-header text-center form-header">
                        <span>Register</span>
                    </div>
                    <div className="card-body">
                        <form onSubmit={handleRegister}>
                            <div className="mb-3">
                                <label htmlFor="firstName" className="form-label">
                                    First name:
                                </label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="firstName"
                                    value={firstName}
                                    placeholder="First name"
                                    onChange={(e) => setFirstName(e.target.value)}
                                />
                            </div>
                            <div className="mb-3">
                                <label htmlFor="lastName" className="form-label">
                                    Last name:
                                </label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="lastName"
                                    value={lastName}
                                    placeholder="Last name"
                                    onChange={(e) => setLastName(e.target.value)}
                                />
                            </div>
                            <div className="mb-3">
                                <label htmlFor="email" className="form-label">
                                    E-mail:
                                </label>
                                <input
                                    type="email"
                                    className={`form-control ${registrationError ? 'is-invalid' : ''}`}
                                    id="email"
                                    value={email}
                                    placeholder="E-mail"
                                    onChange={(e) => setEmail(e.target.value)}
                                />
                                {registrationError && (
                                    <div className="invalid-feedback form-label-extras">
                                        {registrationError}
                                    </div>
                                )}
                            </div>
                            <div className="mb-3">
                                <label htmlFor="password" className="form-label">
                                    Password:
                                </label>
                                <input
                                    type="password"
                                    className="form-control"
                                    id="password"
                                    value={password}
                                    placeholder="Password"
                                    onChange={(e) => setPassword(e.target.value)}
                                />
                            </div>
                            <div className="mb-3">
                                <label htmlFor="repeatPassword" className="form-label">
                                    Confirm password:
                                </label>
                                <input
                                    type="password"
                                    className={`form-control ${repeatPasswordError ? 'is-invalid' : ''}`}
                                    id="repeatPassword"
                                    value={repeatPassword}
                                    placeholder="Confirm password"
                                    onChange={(e) => setRepeatPassword(e.target.value)}
                                />
                                {repeatPasswordError && (
                                    <div className="invalid-feedback form-label-extras">
                                        {repeatPasswordError}
                                    </div>
                                )}
                            </div>
                            <div className="text-center sign-button-container">
                                <button type="submit"
                                        className="btn btn-block d-grid gap-2 col-6 mx-auto action-button">
                                    Register
                                </button>
                            </div>
                        </form>
                        <div className="mt-3 text-center form-label-extras">
                            <p>
                                Already have an account?{" "}
                                <Link to="/login" className="text-decoration-none href-color">
                                    Sign in
                                </Link>
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Register;
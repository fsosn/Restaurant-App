import '../AuthForm.css';
import {useContext, useState} from 'react';
import {Link, useLocation, useNavigate} from 'react-router-dom';
import {AuthContext} from '../../../auth/AuthContext.jsx';
import validate from '../utils/validate.jsx';

const LoginForm = () => {
    const navigate = useNavigate();
    const auth = useContext(AuthContext);
    const location = useLocation();
    const [, setPassword] = useState('');

    let from = location.state?.from?.pathname || '/';

    const handlePasswordChange = (e) => {
        const newPassword = e.target.value;
        setPassword(newPassword);
    };

    function handleSubmit(event) {
        event.preventDefault();

        const formData = new FormData(event.currentTarget);
        const username = formData.get('email');
        const password = formData.get('password');

        if (validate(password)) {
            auth.signIn(username, password, () => {
                navigate(from, {replace: true});
            });
        }
    }

    return (
        <div className="container">
            <div className="col-4">
                <div className="card">
                    <div className="card-header text-center form-header">
                        <span>Sign In</span>
                    </div>
                    <div className="card-body">
                        <form onSubmit={handleSubmit}>
                            <div className="mb-3">
                                <label htmlFor="email" className="form-label">
                                    E-mail:
                                </label>
                                <input
                                    name="email"
                                    type="email"
                                    className="form-control"
                                    id="email"
                                    placeholder="E-mail"
                                />
                            </div>
                            <div className="mb-3">
                                <label htmlFor="password" className="form-label">
                                    Password:
                                </label>
                                <input
                                    name="password"
                                    type="password"
                                    className="form-control"
                                    id="password"
                                    placeholder="Password"
                                    onChange={handlePasswordChange}
                                />
                            </div>
                            <div className="text-center sign-button-container">
                                <button
                                    type="submit"
                                    className="btn btn-block d-grid gap-2 col-6 mx-auto action-button"
                                >
                                    Sign in
                                </button>
                            </div>
                        </form>
                        <div className="mt-3 text-center form-label-extras">
                                <span>
                                    Don&apos;t have an account?{" "}
                                    <Link to="/register" className="text-decoration-none href-color">
                                        Register
                                    </Link>
                                </span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default LoginForm;
import Page from "./template/Page.jsx";
import Headline from "../components/headline/Headline.jsx";
import Menu from "../components/menu/Menu.jsx";

const MainPage = ({ toggleCartVisibility, cartItems, isCartVisible, addToCart, updateCartItems }) => {
    let title = "MarioLuigi";
    return (
        <Page pageTitle={title} toggleCartVisibility={toggleCartVisibility} cartItems={cartItems} isCartVisible={isCartVisible} updateCartItems={updateCartItems}>
            <Headline title={title} />
            <Menu addToCart={addToCart} />
        </Page>
    );
};

export default MainPage;

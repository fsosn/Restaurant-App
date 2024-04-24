import Page from "./template/Page.jsx";
import Headline from "../components/headline/Headline.jsx";
import Menu from "../components/menu/Menu.jsx";

const MainPage = () => {
    let title = "MarioLuigi";
    return (
        <Page pageTitle={title}>
            <Headline title={title}/>
            <Menu/>
        </Page>
    );
};

export default MainPage;
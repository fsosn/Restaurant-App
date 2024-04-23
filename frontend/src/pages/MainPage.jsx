import Page from "./template/Page.jsx";
import Headline from "../components/headline/Headline.jsx";

const MainPage = () => {
    let title = "MarioLuigi";
    return (
        <Page pageTitle={title}>
            <Headline title={title}/>
        </Page>
    );
};

export default MainPage;
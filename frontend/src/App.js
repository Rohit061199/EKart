import logo from './logo.svg';
import './App.css';
import Navigation from './customer/components/Navigation/Navigation.jsx'
import HomePage from './customer/pages/HomePage/HomePage';
import Footer from './customer/components/Footer/Footer';
import Product from './customer/components/Product/Product.jsx';
import ProductDetails from './customer/components/ProductDetails/ProductDetails.jsx';

function App() {
  return (
    <div className="App">
      <Navigation/>
      <div>
       {/* <HomePage/> */}
       {/* <Product/>*/}
       <ProductDetails/>
      </div>
      <Footer/>
    </div>
  );
}

export default App;
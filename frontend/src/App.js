import logo from './logo.svg';
import './App.css';
import Navigation from './customer/components/Navigation/Navigation.jsx'
import HomePage from './customer/pages/HomePage/HomePage';
import Footer from './customer/components/Footer/Footer';
import Product from './customer/components/Product/Product.jsx';
import ProductDetails from './customer/components/ProductDetails/ProductDetails.jsx';
import Cart from './customer/components/Cart/Cart.jsx';
import Checkout from './customer/components/Checkout/Checkout.jsx';
import Order from './customer/components/Order/Order.jsx';
import OrderDetails from './customer/components/Order/OrderDetails.jsx';
import { Route, Routes } from 'react-router-dom';
import CustomerRoutes from './Routers/CustomerRoutes.jsx';

function App() {
  return (
    <div className="App">
<Routes>
  <Route path='/*' element={<CustomerRoutes/>}></Route>
</Routes>

      
      
       
      </div>
      
  );
}

export default App;

import { useState } from 'react';
import NameGenerator from './components/NameGenerator';

function App() {
  const [isSidebarOpen, setIsSidebarOpen] = useState<boolean>(true);

  const toggleSidebar = () => setIsSidebarOpen(!isSidebarOpen);

  return (
    <div className="flex min-h-screen w-full"> {/* Ensure full viewport width and height */}
      {/* Sidebar */}
      <div
        className={`${
          isSidebarOpen ? 'w-100' : 'w-16'
        } bg-gray-800 text-white overflow-hidden transition-all duration-300 z-10`} // z-10 for overlap fix
      >
        <div className="p-4">
          <h1 className={`text-xl font-bold ${isSidebarOpen ? 'block' : 'hidden'}`}>
            Worldbuilder Suite
          </h1>
          <ul className="mt-4 space-y-2">
            <li className={`cursor-pointer ${isSidebarOpen ? 'hover:text-gray-300' : 'text-center'}`}>
              {isSidebarOpen ? 'Name Generator' : 'NG'}
            </li>
            <li className={`text-gray-500 ${isSidebarOpen ? '' : 'text-center'}`}>
              {isSidebarOpen ? 'Loremaster (Coming Soon)' : 'L'}
            </li>
            <li className={`text-gray-500 ${isSidebarOpen ? '' : 'text-center'}`}>
              {isSidebarOpen ? 'Character Prototyper (Coming Soon)' : 'CP'}
            </li>
            <li className={`text-gray-500 ${isSidebarOpen ? '' : 'text-center'}`}>
              {isSidebarOpen ? 'Plot Weaver (Coming Soon)' : 'PW'}
            </li>
          </ul>
        </div>
      </div>

      {/* Main Content */}
      <div className="flex-1 bg-gray-100 min-h-screen"> {/* Ensure full height */}
        <div className="p-4 w-full h-full"> {/* Full width and height for inner content */}
          <button
            className="mb-4 px-4 py-2 bg-teal-500 text-black rounded hover:bg-teal-600"
            onClick={toggleSidebar}
          >
            {isSidebarOpen ? 'Close Sidebar' : 'Open Sidebar'}
          </button>
          <NameGenerator />
        </div>
      </div>
    </div>
  );
}

export default App;
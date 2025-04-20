import { useState } from 'react';
import axios from 'axios';

function NameGenerator() {
  const [description, setDescription] = useState('');
  const [names, setNames] = useState([]);
  const [loading, setLoading] = useState(false);

  const handleGenerate = async () => {
    setLoading(true);
    try {
      const response = await axios.post('http://localhost:8080/api/name/generate', {
        description,
      });
      const nameList = response.data.split(',').map(name => name.trim());
      setNames(nameList);
    } catch (error) {
      console.error('Error generating names:', error);
      setNames(['Error generating names']);
    }
    setLoading(false);
  };

  return (
    <div className="max-w-lg">
      <h2 className="text-2xl font-bold mb-4">Name Generator</h2>
      <input
        className="w-full p-2 mb-4 border rounded"
        placeholder="Describe the character (e.g., male dwarf, Scottish, mage)"
        value={description}
        onChange={(e) => setDescription(e.target.value)}
      />
      <button
        className={`w-full p-2 text-white rounded ${
          loading ? 'bg-teal-300 cursor-not-allowed' : 'bg-teal-500 hover:bg-teal-600'
        }`}
        onClick={handleGenerate}
        disabled={loading}
      >
        {loading ? 'Generating...' : 'Generate Names'}
      </button>
      {names.length > 0 && (
        <div className="mt-4">
          <h3 className="font-bold">Generated Names:</h3>
          <ul className="list-disc pl-5 mt-2">
            {names.map((name, index) => (
              <li key={index}>{name}</li>
            ))}
          </ul>
        </div>
      )}
    </div>
  );
}

export default NameGenerator;
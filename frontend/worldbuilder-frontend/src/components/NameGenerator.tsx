import { useState } from 'react';
import axios from 'axios';

function NameGenerator() {
  const [description, setDescription] = useState<string>('');
  const [names, setNames] = useState<string[]>([]);
  const [loading, setLoading] = useState<boolean>(false);

  const handleGenerate = async () => {
    setLoading(true);
    try {
      const response = await axios.post<string>('http://localhost:8080/api/name/generate',
      description,
          {
              headers: {
                  'Content-Type': 'text/plain',
              },
          }
      );
      const nameList = response.data.split(',').map((name: string) => name.trim());
      setNames(nameList);
    } catch (error) {
      console.error('Error generating names:', error);
      setNames(['Error generating names']);
    }
    setLoading(false);
  };

  return (
    <div className="w-full h-full flex flex-col">
      <h2 className="text-2xl font-bold mb-4">Name Generator</h2>
      <input
        className="w-full p-2 mb-4 border rounded focus:outline-none focus:ring-2 focus:ring-teal-500"
        placeholder="Describe the character (e.g., male dwarf, Scottish, mage)"
        value={description}
        onChange={(e) => setDescription(e.target.value)}
      />
      <button
        className={`w-full p-2 rounded font-semibold text-black ${
          loading
            ? 'bg-teal-300 cursor-not-allowed'
            : 'bg-teal-600 hover:bg-teal-700'
        }`}
        onClick={handleGenerate}
        disabled={loading}
      >
        {loading ? 'Generating...' : 'Generate Names'}
      </button>
      {names.length > 0 && (
        <div className="mt-4 flex-1 overflow-auto">
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
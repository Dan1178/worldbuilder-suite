import { useState } from 'react';
import axios from 'axios';

function ImageGenerator() {
    const [description, setDescription] = useState<string>('');
    const [imageUrl, setImageUrl] = useState<string | null>(null);
    const [loading, setLoading] = useState<boolean>(false);

    const handleGenerate = async () => {
        setLoading(true);
        setImageUrl(null);
        try {
            const response = await axios.post<string>('http://localhost:8080/api/image/generate', {
                description,
            });
            setImageUrl(response.data);
        } catch (error) {
            console.error('Error generating image:', error);
            setImageUrl(null);
        }
        setLoading(false);
    };

    return (
        <div className="w-full h-full flex flex-col">
            <h2 className="text-2xl font-bold mb-4">Character Prototyper</h2>
            <input
                className="w-full p-2 mb-4 border rounded focus:outline-none focus:ring-2 focus:ring-teal-500"
                placeholder="Describe the character (e.g., female elven archer)"
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
                {loading ? 'Generating...' : 'Generate Image'}
            </button>
            {imageUrl && (
                <div className="mt-4 flex-1 overflow-auto">
                    <h3 className="font-bold">Generated Image:</h3>
                    <img
                        src={imageUrl}
                        alt="Generated character"
                        className="mt-2 max-w-full h-auto rounded"
                    />
                </div>
            )}
        </div>
    );
}

export default ImageGenerator;